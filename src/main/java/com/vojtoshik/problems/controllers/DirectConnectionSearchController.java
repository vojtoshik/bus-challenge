package com.vojtoshik.problems.controllers;

import com.vojtoshik.problems.models.SearchResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@RestController
public class DirectConnectionSearchController {

    @RequestMapping(method = RequestMethod.GET, path = "/api/direct", params = {"dep_sid", "arr_sid"})
    public SearchResultResponse findConnection(@RequestParam("dep_sid") int departureLocationId,
                                               @RequestParam("arr_sid") int arrivalLocationId) {

        return new SearchResultResponse(departureLocationId, arrivalLocationId, false);
    }
}
