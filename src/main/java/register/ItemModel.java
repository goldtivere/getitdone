/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

/**
 *
 * @author Gold
 */
public class ItemModel {
    private int id;
    private int vendorfk;
    private int categoryfk;
    private String itemname;
    private double amount;
    private String imgLink;
    private String imgPath;
    private String itemDescritpion;
    private int quantity;
    private double agentPercentage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorfk() {
        return vendorfk;
    }

    public void setVendorfk(int vendorfk) {
        this.vendorfk = vendorfk;
    }

    public int getCategoryfk() {
        return categoryfk;
    }

    public void setCategoryfk(int categoryfk) {
        this.categoryfk = categoryfk;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getItemDescritpion() {
        return itemDescritpion;
    }

    public void setItemDescritpion(String itemDescritpion) {
        this.itemDescritpion = itemDescritpion;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAgentPercentage() {
        return agentPercentage;
    }

    public void setAgentPercentage(double agentPercentage) {
        this.agentPercentage = agentPercentage;
    }
    
    
            
    
}
