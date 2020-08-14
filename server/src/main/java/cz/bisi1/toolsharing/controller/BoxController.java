package cz.bisi1.toolsharing.controller;

import cz.bisi1.toolsharing.entity.BoxEntity;
import cz.bisi1.toolsharing.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <h1>BoxController</h1>
 * The BoxController class implements an application that
 * manages boxes in frontend.
 */
@RestController
@RequestMapping("api/box")
@CrossOrigin
public class BoxController {

    @Autowired
    private BoxService boxService;

    /**
     * Returns all boxes from database.
     * @return List of BoxEntity in json format with status 200.
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> getAllBoxes() {
        return ResponseEntity.ok(boxService.findAllBoxes());
    }

    /**
     * If box with that id exists method will return it.
     * @param id of the box that method will return.
     * @return BoxEntity in json format with status 200 or nothing with status 404.
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getBoxById(@PathVariable(value = "id") Long id) {
        Optional<BoxEntity> box = boxService.findBoxById(id);
        if (!box.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(box.get());
    }

    /**
     * Creates new box and saves it in the database.
     * @param box that method will save in database.
     * @return BoxEntity in json format with status 200.
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createBox(@Valid @RequestBody BoxEntity box) {
        return ResponseEntity.ok(boxService.saveBox(box));
    }

    /**
     * If box with that id do exists changes data in box with id in first parameter
     * for data in second parameter.
     * @param id of the box that method will update.
     * @param box containing the new data.
     * @return BoxEntity in json format with status 200 or nothing with status 404.
     */
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateBox(@PathVariable(value = "id") Long id, @Valid @RequestBody BoxEntity box) {
        if (!boxService.findBoxById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boxService.updateBox(id,box));
    }

    /**
     * If box with that id do exists deletes it from database.
     * @param id of the box that method will delete.
     * @return nothing with status 200 or nothing with status 404.
     */
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteBox(@PathVariable(value = "id") Long id) {
        if (!boxService.findBoxById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        boxService.deleteBox(id);
        return ResponseEntity.ok().build();
    }
}

