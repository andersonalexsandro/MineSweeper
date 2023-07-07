package br.com.andersonalexsandro.mf.model;

import br.com.andersonalexsandro.mf.exceptions.ExplosedRuntimeException;
import br.com.andersonalexsandro.mf.model.interfaces.MineCounter;
import br.com.andersonalexsandro.mf.model.interfaces.Explosable;
import br.com.andersonalexsandro.mf.model.interfaces.Flaggable;

import java.util.Objects;

public class FieldState implements MineCounter, Explosable, Flaggable {

    private boolean checked;
    private int numberOfMinesAround;
    private boolean flagged;
    private boolean mine;
    private boolean explosed;

    public FieldState(){
    }

    public void check(){
        setChecked(true);
        if(isMine()) explodes();
    }

    public void disCheck(){
        setChecked(false);
    }

    private void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setNumberOfMinesAround(int bombsAround) {
        this.numberOfMinesAround = bombsAround;
    }

    protected void flag(){
        setFlagged(true);
    }

    public void disFlag(){
        setFlagged(false);
    }

    private void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void explodes(){
        setExplosed(true);
        throw new ExplosedRuntimeException("Mine explosed!");
    }

    private void setExplosed(boolean explosed) {
        this.explosed = explosed;
    }

    @Override
    public void spawnMine() {
        setMine(true);
    }

    public void disSpawnBomb(){
        setMine(false);
    }

    private void setMine(boolean mine) {
        this.mine = mine;
    }

    @Override
    public boolean isMine() {
        return mine;
    }

    @Override
    public boolean isExplosed() {
        return explosed;
    }

    @Override
    public boolean isFlagged() {
        return flagged;
    }

    @Override
    public int getNumberOfMinesAround() {
        return numberOfMinesAround;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldState that = (FieldState) o;
        return checked == that.checked && numberOfMinesAround == that.numberOfMinesAround && flagged == that.flagged && mine == that.mine && explosed == that.explosed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(checked, numberOfMinesAround, flagged, mine, explosed);
    }

    @Override
    public String toString() {
        if(isFlagged()) return GameRules.getFlagIcon();
        if(!isChecked()) return GameRules.getNotCheckedIcon();
        if(isMine() && isChecked()) return GameRules.getMineIcon();
        if(isMine() && !isChecked()) return GameRules.getNotCheckedIcon();
        if(numberOfMinesAround==0) return GameRules.getDefultIcon();
        return ""+ numberOfMinesAround;
    }
}
