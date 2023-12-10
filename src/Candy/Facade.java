package Candy;

public class Facade {
    private Biscuit biscuit;
    private Chocolate chocolate;
    private  Marshmallow  marshmallow;
    private Sweet sweet;

    public All addBiscuitMenu(){
        All biscuit = new Biscuit();
        biscuit.setName(biscuit.addName());
        biscuit.setWeight(biscuit.addWeight());
        return biscuit;
    }
    public All addChocolateMenu(){
        All chocolate = new Chocolate();
        chocolate.setName(chocolate.addName());
        chocolate.setWeight(chocolate.addWeight());
        return chocolate;
    }
    public All addMarshmallowMenu(){
        All marshmallow = new Marshmallow();
        marshmallow.setName(marshmallow.addName());
        marshmallow.setWeight(marshmallow.addWeight());
        return marshmallow;
    }
    public All addSweetMenu(){
        All sweet = new Sweet();
        sweet.setName(sweet.addName());
        sweet.setWeight(sweet.addWeight());
        return sweet;
    }

}
