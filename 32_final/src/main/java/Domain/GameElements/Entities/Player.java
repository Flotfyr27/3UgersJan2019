package Domain.GameElements.Entities;

public class Player {
    private int pPos;
    private Account account;
    private String name;
    private boolean isActive;

    public Player(String name) {
        this.name = name;
        pPos = 0;
        account = new Account();
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

    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }
}