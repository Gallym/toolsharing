package cz.bisi1.toolsharing.controller;

import cz.bisi1.toolsharing.entity.ReservationEntity;
import cz.bisi1.toolsharing.entity.ToolEntity;
import cz.bisi1.toolsharing.service.ReservationService;
import cz.bisi1.toolsharing.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <h1>ToolController</h1>
 * The ToolController class implements an application that
 * manages tools in frontend.
 */
@RestController
@RequestMapping("/api/tool")
@CrossOrigin
public class ToolController {
    @Autowired
    private ToolService toolService;

    @Autowired
    private ReservationService reservationService;

    /**
     * Returns all tools that have state AVAILABLE from database.
     * @return List of ToolEntity in json format with status 200.
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> getAllAvailableTools() {
        return ResponseEntity.ok(toolService.findAllAvailableTools());
    }

    /**
     * Returns all tools that have state AVAILABLE and contain String name as substring of their name.
     * @param name by that String we find tools.
     * @return List of ToolEntity in json format with status 200.
     */
    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<?> getAllAvailableToolsByName(@RequestParam String name) {
        return ResponseEntity.ok( toolService.findAllAvailableByNameLike(name));
    }

    /**
     * If tool with that id exists method will return it.
     * @param id of the tool that method will return.
     * @return ToolEntity in json format with status 200 or nothing with status 404.
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getToolById(@PathVariable(value = "id") Long id) {
        Optional<ToolEntity> tool = toolService.findToolById(id);
        if (!tool.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tool.get());
    }

    /**
     * First it checks if the tool with that id exists.
     * Then finds all active reservations of that tool,
     * iterates through all of the dates of reservations and
     * sets in the array true om the specific place if
     * the tool that day is reserved.
     * @param id of the tool .
     * @return array of 30 Booleans as json or nothing with status 404.
     */
    @GetMapping(value = "/{id}/dates", produces = "application/json")
    public ResponseEntity<?> getThirtyDays(@PathVariable(value = "id") Long id) {
        Optional<ToolEntity> tool = toolService.findToolById(id);
        if (!tool.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservationService.getThirtyDaysOfTool(tool.get()));
    }

    /**
     * Creates new tool and saves it in the database.
     * @param tool that method will save in database.
     * @return ToolEntity in json format with status 200.
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createTool(@Valid @RequestBody ToolEntity tool) {
        return ResponseEntity.ok(toolService.saveTool(tool));
    }

    /**
     * If tool with that id do exists changes data in reservation with id in first parameter
     * for data in second parameter.
     * @param id of the tool that method will update.
     * @param tool all the new data.
     * @return ToolEntity in json format with status 200 or nothing with status 404.
     */
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateTool(@PathVariable(value = "id") Long id, @Valid @RequestBody ToolEntity tool) {
        if (!toolService.findToolById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toolService.updateTool(id, tool));
    }

    /**
     * If tool with that id do exists deletes it from database.
     * @param id of the box that method will delete.
     * @return nothing with status 200 or nothing with status 404.
     */
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteTool(@PathVariable(value = "id") Long id) {
        if (!toolService.findToolById(id).isPresent()) {
            return  ResponseEntity.notFound().build();
        }
        toolService.deleteTool(id);
        return ResponseEntity.ok().build();
    }
}

