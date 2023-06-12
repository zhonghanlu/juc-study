package org.example.advanced;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class demo03 {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("tb"),
            new NetMall("pdd")
    );

    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall ->
                        String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                        CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {

//        getPrice(list, "mysql").forEach(System.out::println);
        getPriceByCompletableFuture(list, "mysql").forEach(System.out::println);
    }
}

class NetMall {

    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
