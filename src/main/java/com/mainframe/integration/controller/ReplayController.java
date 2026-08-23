package com.mainframe.integration.controller;

import com.mainframe.integration.service.DlqReplayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/replay")
public class ReplayController {
    private final DlqReplayService replayService;

    public ReplayController(DlqReplayService replayService) {
        System.out.println("ReplayController created");
        this.replayService = replayService;
    }

    @PostMapping
    public String replay() {
        System.out.println("Inside ReplayController");
        replayService.replayMessages();
        System.out.println("After replayService");
        return "Replay completed";
    }
}
