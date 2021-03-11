package com.szcinda.express.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class HostDto implements Serializable {
    private String name;
    private String userName;
    private String macAddress;
    private String status;
    private String id;
    private List<Flow> flows = new ArrayList<>();
    @Data
    public static class Flow implements Serializable{
        private String index;
        private String name;
        private String status;
    }
}
