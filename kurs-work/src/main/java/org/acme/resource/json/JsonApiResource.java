package org.acme.resource.json;


import org.acme.resource.json.dto.TestDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/json")
public class JsonApiResource {

    @Inject
    Logger log;

    @Operation( summary = "this method takes and input stream",
    description = "the input stream is logged and also returned as output")
    @GET
    @Path("/inputParameter/{inputString}")
    @Produces(MediaType.TEXT_PLAIN)
    public String inputParameter(
            @Parameter(name = "input", description = "The TestDTO Input object to store", required = true, allowEmptyValue = false)
            @PathParam("inputString") String inputString){
        log.infov("log: {0}", inputString);
        return inputString;
    }

    // http://localhost:8080/json/queryParameter?qp=inputText&qp2=text2
    @GET
    @Path("/queryParameter")
    @Produces(MediaType.TEXT_PLAIN)
    public String queryParameter(
            @QueryParam("qp") String qp,
            @QueryParam("qp2") String qP2
            ){
        log.infov("log QueryParam: {0}", qp);
        return qp + " und " + qP2;
    }

    @GET
    @Path("header")
    public String checkBrowser(@HeaderParam("User-Agent") String whichBrowser) {
        return "Browser is "+whichBrowser;
    }

    // Reading REST Parameters Programmatically
    // z.b. http://localhost:8080/json/context?username=chris
    @GET
    @Path("context")
    public Response login(@Context UriInfo info) {
        String id = info.getQueryParameters().getFirst("username");
        return Response
                .status(200)
                .entity("login called with id: " + id)
                .build();
    }

    @GET
    @Path("httpheaders")
    public String getRequestHeaders(@Context HttpHeaders hh) {

        StringBuffer sb = new StringBuffer();

        MultivaluedMap<String, String> headerParameters = hh.getRequestHeaders();
        for (String key : headerParameters.keySet()) {
            sb.append(key + ": " + headerParameters.get(key));
            sb.append(" ");
        }

        Map<String, Cookie> params = hh.getCookies();
        for (String key : params.keySet()) {
            sb.append(key + ": " + params.get(key));
        }
        return sb.toString();
    }


    @GET
    @Path("/objectOutput")
    @Produces(MediaType.APPLICATION_JSON)
    public TestDTO objectOutput(){
        log.infov("objectOutput {0}", "");

        TestDTO dto = new TestDTO();
        dto.setName("name");
        dto.setVorname("voranme");

        return dto;
    }

    @Operation(summary = "this method inserts a new test message",
               description = "the created message is then also returned")
    @PUT
    @Path("createTestMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TestDTO updateTestMessage(TestDTO input) {
        log.infov("got object : {0}", input.toString());

        return input;
    }

    @POST
    @Path("createTestMessage")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public TestDTO createTestMessagePost(TestDTO input) {
        log.infov("sendChatMessagePost got object : {0}", input.toString());

        return input;
    }


    @GET
    @Path("/objectListOutput")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<TestDTO> objectListOutput(){
        log.infov("objectOutput {0}", "");

        TestDTO dto = new TestDTO();
        dto.setName("name");
        dto.setVorname("voranme");

        TestDTO dto2 = new TestDTO();
        dto.setName("name2");
        dto.setVorname("voranme2");

        List result = new ArrayList();
        result.add(dto);
        result.add(dto2);

        return result;
    }

}
