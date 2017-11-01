/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedManagement;

public class Options {

    private String id;
    private String name;
    private String qte;
    private String price;
    private String catId;

    public Options() {
    }

    public Options(String Id, String Name, String Qte, String Price, String CatId) {

        this.id = Id;
        this.name = Name;
        this.qte = Qte;
        this.price = Price;
        this.catId = CatId;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String Qte) {
        this.qte = Qte;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String Price) {
        this.price = Price;
    }

    public String getCatID() {
        return catId;
    }

    public void setCatID(String CatID) {
        this.catId = CatID;
    }

}
