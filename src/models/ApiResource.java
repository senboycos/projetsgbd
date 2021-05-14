package models;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.Date;
import java.util.List;

public class ApiResource {


    private static final Logger LOGGER = LoggerFactory.getLogger(ApiResource.class);

    private final Service_com service_com = new Service_com();
    private final Service_loc service_loc = new Service_loc();

    public Router getSubRouter(final Vertx vertx) {
        final Router subRouter = Router.router(vertx);

        // Body handler
        subRouter.route("/*").handler(BodyHandler.create());

        // Routes
        subRouter.get("/").handler(this::getAllCommunique);
        subRouter.get("/:id").handler(this::getOneCommunique);
        subRouter.post("/").handler(this::createOneCommunique);
        subRouter.get("/localite/").handler(this::getAllLocalite);
        subRouter.get("/localite/:id").handler(this::getOneLocalite);
        subRouter.post("/localite/").handler(this::createOneLocalite);

        return subRouter;
    }

    private void getAllCommunique(final RoutingContext routingContext) {
        LOGGER.info("Dans getAllCommunique...");

        final List<Communique> communiques = service_com.findAll();

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("com", communiques);
        jsonResponse.put("my-name", "COS");

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));
    }
    private void getAllLocalite(final RoutingContext routingContext) {
        LOGGER.info("Dans getAllLocalite...");

        final List<Localite> localites = service_loc.findAll();

        final JsonObject jsonResponse = new JsonObject();
        jsonResponse.put("loc", localites);
        jsonResponse.put("my-name", "COS");

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(jsonResponse));
    }

    private void getOneCommunique(final RoutingContext routingContext) {
        LOGGER.info("Dans getOneCommunique...");

        final String id = routingContext.request().getParam("id");

        final Communique communique = service_com.findById(Integer.parseInt(id));

        if (communique == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No communique can be found for the specified id:" + id);
            errorJsonResponse.put("id", id);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
            return;
        }
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(communique));
    }
    private void getOneLocalite(final RoutingContext routingContext) {
        LOGGER.info("Dans getOneLocalite...");

        final String id = routingContext.request().getParam("id");

        final Localite localite = service_loc.findById(Integer.parseInt(id));

        if (localite == null) {
            final JsonObject errorJsonResponse = new JsonObject();
            errorJsonResponse.put("error", "No localite can be found for the specified id:" + id);
            errorJsonResponse.put("id", id);

            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(Json.encode(errorJsonResponse));
            return;
        }
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(Json.encode(localite));
    }

    private void createOneCommunique(final RoutingContext routingContext) {
        LOGGER.info("Dans createOneCommunique...");
        final JsonObject body = routingContext.getBodyAsJson();
        final Integer id = body.getInteger("id");
        final Integer nbreTest = body.getInteger("nbreTest");
        final Integer nbNouveauxCas = body.getInteger("nbNouveauxCas");
        final Integer nbCasContacts = body.getInteger("nbCasContacts");
        final Integer nbCasCommunautaires = body.getInteger("nbCasCommunautaires");
        final Integer nbrGueris = body.getInteger("nbrGueris");
        final Integer nbDeces = body.getInteger("nbDeces");
        final String nomFichierSource = body.getString("nomFichierSource");
        final String dateHeureExtraction = body.getString("dateHeureExtraction");
        // TODO Vérification des champs...
        final Communique communique = new Communique(id,nbreTest,nbNouveauxCas,nbCasContacts,nbCasCommunautaires,nbrGueris,nbDeces,nomFichierSource,dateHeureExtraction);
         service_com.add(communique);
        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json")
                .end(Json.encode("Succes"));
    }
    private void createOneLocalite(final RoutingContext routingContext) {
        LOGGER.info("Dans createOneLocalite...");
        final JsonObject body = routingContext.getBodyAsJson();
        final Integer id = body.getInteger("id");
        final String nomLocalite = body.getString("nomLocalite");
        final Integer nbCas = body.getInteger("nbCas");
        final Integer id_communique = body.getInteger("id_communique");
        // TODO Vérification des champs...
        final Localite localite = new Localite(id,nomLocalite,nbCas,id_communique);
        service_loc.add(localite);
        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json")
                .end(Json.encode("Succes"));
    }
}
