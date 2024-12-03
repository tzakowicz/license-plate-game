package net.gilmor.plate.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.gilmor.plate.bean.GameBean;
import net.gilmor.plate.model.GameSettings;
import net.gilmor.plate.model.SightingClaim;
import net.gilmor.plate.util.JsonUtil;
import net.gilmor.plate.util.ResponseUtil;

@Path("/game")
public class GameController {

    @Inject
    private GameBean game;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response platesUp() {
        return Response.ok(true).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/playing")
    public Response isGameRunning() {
        try {
            return Response.ok(game.playing()).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/start")
    public Response startGame(GameSettings settings) {
        try {
            if (game.initialize(settings)) {
                game.start();
            }
            return Response.ok(game.playing()).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/end")
    public Response endGame() {
        try {
            if (!game.playing()) {
                return Response.ok(game.playing()).build();
            }
            game.end();
            return Response.ok(game.playing()).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/territory")
    public Response currentTerritory() {
        try {
            return Response.ok(game.currentTerritory()).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/territory/found")
    public Response foundTerritory() {
        try {
            return Response.ok(JsonUtil.buildJson(game.foundTerritories())).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/crossBorder")
    public Response crossBorder(String code) {
        try {
            game.crossBorder(code);
            return Response.ok(true).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/borders")
    public Response borderStates() {
        try {
            return Response.ok(JsonUtil.buildJson(game.borderTerritories())).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countries")
    public Response countries() {
        try {
            return Response.ok(JsonUtil.buildJson(game.countries())).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/scores")
    public Response scoreboard() {
        try {
            return Response.ok(JsonUtil.buildJson(game.scoreboard())).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sighting")
    public Response addSighting(SightingClaim claim) {
        try {
            game.addSighting(claim);
            return Response.ok(true).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sighting/last")
    public Response lastSighting() {
        try {
            return Response.ok(JsonUtil.buildJson(game.lastSighting())).build();
        } catch (IllegalStateException e) {
            return ResponseUtil.failedResponse(e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e.getMessage());
        }
    }
}
