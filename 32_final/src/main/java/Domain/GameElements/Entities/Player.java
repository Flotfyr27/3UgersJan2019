package Domain.GameElements.Entities;

public class Player {
    private int pPos;
    private main.java.Domain.GameElements.Entities.Account account;
    private String name;
    private boolean isActive;

    public Player(String name) {
        this.name = name;
        pPos = 0;
        account = new main.java.Domain.GameElements.Entities.Account();
        isActive = true;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setPos(int pPos) {
        this.pPos = pPos;
    }

    public int getPos() {
        return pPos;
    }

    public main.java.Domain.GameElements.Entities.Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }
}