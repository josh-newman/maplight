package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
  public static void index() {
    render();
  }
    
  public static void byDonors(String donor, String recipient, String date_start, String date_end,
      String location_from, String location_to, boolean download) {
    List<CandidateContributions> cc = CandidateContributions.get(params);
    Object total = null;
    if (cc.size() > 0) {
      total = CandidateContributions.getTotal(params);
    }

    if (download) {
      response.setHeader("Content-Disposition", "attachment; filename=download.csv");
      renderTemplate("Application/CandidateContributions.csv", cc);
    } else {
      renderTemplate("Application/CandidateContributions.html", cc, total, donor, recipient, date_start, 
          date_end, location_from, location_to);
    }
  }
}
