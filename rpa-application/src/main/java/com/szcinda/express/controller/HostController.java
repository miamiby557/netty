package com.szcinda.express.controller;

/*import com.szcinda.express.HostService;
import com.szcinda.express.UserService;
import com.szcinda.express.configuration.UserLoginToken;
import com.szcinda.express.controller.dto.HostDto;
import com.szcinda.express.controller.dto.Result;
import com.szcinda.express.controller.netty.NettyChannelInboundHandlerAdapter;
import com.szcinda.express.dto.CreateHostDto;
import com.szcinda.express.params.PageResult;
import com.szcinda.express.params.QueryHostParams;
import com.szcinda.express.persistence.Host;
import com.szcinda.express.persistence.User;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;*/
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/host")
public class HostController {
    /*private final UserService userService;

    public HostController(HostService hostService, UserService userService) {
        this.hostService = hostService;
        this.userService = userService;
    }

    @GetMapping("/queryByMobile/{openId}")
    public List<HostDto> queryByMobile(@PathVariable("openId") String openId) {
        List<HostDto> hostDtos = new ArrayList<>();
        User user = userService.findByOpenId(openId);
        if (user == null) {
            return hostDtos;
        }
        List<Host> hostList = hostService.findAll(user.getId());
        ConcurrentHashMap<String, List<String>> flowMap = NettyChannelInboundHandlerAdapter.FLOW_MAP;
        System.out.println("流程池:"+flowMap.size());
        Set<String> keys = flowMap.keySet(); //取出所有的key值
        for (String key : keys) {
            System.out.println("key：" + key);
        }
        if (hostList.size() > 0) {
            hostList.forEach(host -> {
                System.out.println("主机:" + host.getMacAddress());
                String macAddress = host.getMacAddress();
                HostDto hostDto = new HostDto();
                BeanUtils.copyProperties(host, hostDto);
                System.out.println("是否包含流程:" + flowMap.containsKey(macAddress));
                if (flowMap.containsKey(macAddress)) {
                    List<String> flows = flowMap.get(macAddress);
                    List<HostDto.Flow> hostFlows = new ArrayList<>();
                    flows.forEach(flow -> {
                        HostDto.Flow item = new HostDto.Flow();
                        String[] strings = flow.split("-");
                        String index = strings[0];
                        String name = strings[1];
                        String status = strings[2];
                        item.setIndex(index);
                        item.setName(name);
                        item.setStatus(status);
                        hostFlows.add(item);
                    });
                    System.out.println("是否包含流程:" + String.join(";", hostFlows.stream().map(HostDto.Flow::getName).collect(Collectors.toList())));
                    hostDto.getFlows().addAll(hostFlows);
                    hostDto.setStatus("在线中");
                } else {
                    hostDto.setStatus("未在线");
                }
                hostDtos.add(hostDto);
            });
        }
        return hostDtos;
    }

    @UserLoginToken
    @PostMapping("/query")
    public PageResult<HostDto> query(@RequestBody QueryHostParams params) {
        List<HostDto> hostDtos = new ArrayList<>();
        if (StringUtils.hasLength(params.getUserName())) {
            List<User> users = userService.findByNameLike(params.getUserName());
            params.getUserIds().addAll(users.stream().map(User::getId).collect(Collectors.toList()));
        }
        PageResult<Host> pageResult = hostService.query(params);
        if (pageResult.getContent().size() > 0) {
            List<String> userIds = pageResult.getContent().stream().map(Host::getUserId).collect(Collectors.toList());
            List<User> userList = userService.findUserByIds(userIds);
            pageResult.getContent().forEach(host -> {
                HostDto hostDto = new HostDto();
                BeanUtils.copyProperties(host, hostDto);
                userList.stream().filter(user -> user.getId().equals(host.getUserId()))
                        .findFirst()
                        .ifPresent(user -> hostDto.setUserName(user.getAccount()));
                hostDtos.add(hostDto);
            });
        }
        return PageResult.of(hostDtos, pageResult.getPage(), pageResult.getPageSize(), pageResult.getTotalElements());
    }

    @UserLoginToken
    @GetMapping("/delete/{hostId}")
    public Result delete(@PathVariable("hostId") String hostId) {
        hostService.delete(hostId);
        return Result.success();
    }

    @UserLoginToken
    @PostMapping("/create")
    public Result create(@RequestBody CreateHostDto createHostDto) {
        hostService.create(createHostDto);
        return Result.success();
    }*/
}
