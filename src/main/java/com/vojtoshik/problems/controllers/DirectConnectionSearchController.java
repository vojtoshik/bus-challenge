package com.vojtoshik.problems.controllers;

import com.vojtoshik.problems.models.SearchResultResponse;
import com.vojtoshik.problems.services.DirectConnectionLookupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author Anton Voitovych <vojtoshik@gmail.com>
 */
@RestController
public class DirectConnectionSearchController {

    @Inject
    private DirectConnectionLookupService lookupService;

    @RequestMapping(method = RequestMethod.GET, path = "/api/direct", params = {"dep_sid", "arr_sid"})
    public SearchResultResponse findConnection(@RequestParam("dep_sid") int departureLocationId,
                                               @RequestParam("arr_sid") int arrivalLocationId) {

        boolean directConnectionExists = lookupService.lookup(departureLocationId, arrivalLocationId);
        return new SearchResultResponse(departureLocationId, arrivalLocationId, directConnectionExists);
    }
}
