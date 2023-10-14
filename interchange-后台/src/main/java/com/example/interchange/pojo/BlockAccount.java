package com.example.interchange.pojo;

/**
 * @author 秃头小刘
 * @date 2022-05-06 10:22 PM
 */


public class BlockAccount {
    private String name;
    private String address;
    private String chain;
    private String words;

    public BlockAccount() {
    }

    public BlockAccount(String name, String address, String chain, String words) {
        this.name = name;
        this.address = address;
        this.chain = chain;
        this.words = words;
    }

    @Override
    public String toString() {
        return "BlockAccount{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", chain='" + chain + '\'' +
                ", words='" + words + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
