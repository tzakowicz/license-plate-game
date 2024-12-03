package net.gilmor.plate.util;

import jakarta.ws.rs.core.Response;
import net.gilmor.plate.model.JsonResponse;

public class ResponseUtil {

    public static <T extends Object> Response failedResponse(T content) {
        return response(new JsonResponse<T>(false, "Error Message", content));
    }

    private static <T extends Object> Response response(JsonResponse<T> obj) {
        return Response.ok(JsonUtil.buildJson(obj)).build();
    }

    public static Response serverErrorResponse(String content) {
        return Response.serverError().entity(content).build();
    }
}
