package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.Player;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.EndGameJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipType;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a proxy controller
 */
public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final Player player;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * @param server the server the proxy will communicate with
   * @param player the player the proxy will communicate with
   * @throws IOException if the server doesn't work
   */
  public ProxyController(Socket server, Player player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
  }

  /**
   * runs the program and waits for a Json message
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      System.err.println("Disconnected");
    }
  }

  /**
   * @param message the message passed in by the server
   */
  private void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();
    JsonNode response = null;

    if ("join".equals(name)) {
      response = handleJoin();
    } else if ("setup".equals(name)) {
      response = handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      response = handleTakeShots();
    } else if ("report-damage".equals(name)) {
      response = handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      response = handleSuccessfulHits(arguments);
    } else if ("end-game".equals(name)) {
      handleEndGame(arguments);
    } else {
      throw new IllegalArgumentException("Invalid message name!");
    }
    createMessage(name, response);
  }

  /**
   * handles the join message from the server
   *
   * @return the response as a Json Node
   */
  private JsonNode handleJoin() {
    JoinJson response = new JoinJson("hakugakusha", "MULTI");
    return JsonUtils.serializeRecord(response);
  }

  /**
   * handles the setup message from the server
   *
   * @param arguments the arguments of setup
   * @return the response as a Json node
   */
  private JsonNode handleSetup(JsonNode arguments) {
    SetupJson request = this.mapper.convertValue(arguments, SetupJson.class);
    List<Ship> fleet = this.player.setup(request.height(), request.width(), request.spec());
    List<ShipJson> ships = new ArrayList<>();
    for (Ship s : fleet) {
      List<Coord> position = s.getPosition();
      Coord origin = position.get(0);
      ShipType type = s.getType();
      CoordJson start = new CoordJson(origin.getX(), origin.getY());
      ships.add(new ShipJson(start, type.getSize(), "HORIZONTAL"));
    }
    FleetJson response = new FleetJson(ships);
    return JsonUtils.serializeRecord(response);
  }

  /**
   * @return the response as a Json node
   */
  private JsonNode handleTakeShots() {
    List<Coord> coords = player.takeShots();
    List<CoordJson> coordJsons = new ArrayList<>();
    for (Coord c : coords) {
      coordJsons.add(new CoordJson(c.getX(), c.getY()));
    }
    CoordinatesJson response = new CoordinatesJson(coordJsons);
    return JsonUtils.serializeRecord(response);
  }

  /**
   * handles the report damage message from the server
   *
   * @param arguments the arguments of report-damage
   * @return the response as a Json Node
   */
  private JsonNode handleReportDamage(JsonNode arguments) {
    CoordinatesJson request = this.mapper.convertValue(arguments, CoordinatesJson.class);
    List<CoordJson> coordJson = request.coordinates();
    List<Coord> shots = new ArrayList<>();
    for (CoordJson c : coordJson) {
      shots.add(new Coord(c.x(), c.y()));
    }
    List<Coord> opponentShots = player.reportDamage(shots);
    List<CoordJson> responseDamage = new ArrayList<>();
    for (Coord coord : opponentShots) {
      responseDamage.add(new CoordJson(coord.getX(), coord.getY()));
    }
    CoordinatesJson response = new CoordinatesJson(responseDamage);

    return JsonUtils.serializeRecord(response);
  }

  /**
   * @param arguments the successful hits arguments given by the server
   * @return the response returned as JsonNode
   */
  private JsonNode handleSuccessfulHits(JsonNode arguments) {
    CoordinatesJson request = this.mapper.convertValue(arguments, CoordinatesJson.class);
    List<Coord> success = new ArrayList<>();
    for (CoordJson c : request.coordinates()) {
      success.add(new Coord(c.x(), c.y()));
    }
    player.successfulHits(success);

    return mapper.createObjectNode();
  }

  /**
   * @param arguments the arguments of the end-game message
   * @return the response as a Json node
   */
  private JsonNode handleEndGame(JsonNode arguments) {
    EndGameJson request = this.mapper.convertValue(arguments, EndGameJson.class);
    player.endGame(request.result(), request.reason());

    return mapper.createObjectNode();
  }

  /**
   * @param name the name of the message
   * @param jsonResponse the json response to be sent to the server
   */
  private void createMessage(String name, JsonNode jsonResponse) {
    MessageJson message = new MessageJson(name, jsonResponse);

    try {
      this.out.println(mapper.writeValueAsString(message));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
