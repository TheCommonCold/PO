package portfel;

import aktywa.JednostkaUczestnictwa;

public class StackJednostekUczestnictwa extends StackAktyw {
    private JednostkaUczestnictwa JednostkaUczestnictwa;

    public StackJednostekUczestnictwa(JednostkaUczestnictwa JednostkaUczestnictwa, float ilosc) {
        this.JednostkaUczestnictwa = JednostkaUczestnictwa;
        setIlosc(ilosc);
    }

    public JednostkaUczestnictwa getJednostkaUczestnictwa() {
        return JednostkaUczestnictwa;
    }

    public void setJednostkaUczestnictwa(JednostkaUczestnictwa JednostkaUczestnictwa) {
        this.JednostkaUczestnictwa = JednostkaUczestnictwa;
    }
}
