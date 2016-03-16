package com.akatsuki.freshy.service.controller;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.akatsuki.freshy.service.actions.ActionResponse;
import com.akatsuki.freshy.service.actions.ActionSmall;
import com.akatsuki.freshy.service.database.DbAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("delete")
public class DeleteAction {

  @POST
  @Path("delete")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public String delete(String request) {
    Gson gson = new GsonBuilder().create();
    ActionSmall action = gson.fromJson(request, ActionSmall.class);
    System.out.println(action);

    DbAdapter dbAdapter = new DbAdapter();
    try {
      dbAdapter.open();
      dbAdapter.pause(action); //TOOD for now it is same as pause, research if deleting rows in database is OK or NOT
      dbAdapter.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    ActionResponse res = new ActionResponse("Action not implemented");
    return (new Gson()).toJson(res);
  }
}
