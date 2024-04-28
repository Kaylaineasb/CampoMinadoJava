package campoMinadov2.visao;

import campoMinadov2.modelo.Campo;
import campoMinadov2.modelo.CampoEvento;
import campoMinadov2.modelo.ICampoObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotaoCampo extends JButton
        implements ICampoObservador , MouseListener {
    private final Color BG_Padrao = new Color(184,184,184);
    private final Color BG_Marcado = new Color(88,179,247);
    private final Color BG_Explosao = new Color(189,66,68);
    private final Color Text_Verde = new Color(0,100,0);
    private Campo campo;
    public BotaoCampo(Campo campo){
        this.campo = campo;
        setBackground(BG_Padrao);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(0));
        addMouseListener(this);
        campo.registrarObservador(this);
    }
    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento){
        switch (evento){
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
        }
    }

    private void aplicarEstiloPadrao() {
    setBackground(BG_Padrao);
        setBorder(BorderFactory.createBevelBorder(0));
    setText("");
    setIcon(null);
    }

    private void aplicarEstiloExplodir() {
        setBackground(BG_Explosao);
        setForeground(Color.WHITE);
        ImageIcon iconeBomba = new ImageIcon(getClass().getResource("./bomba.jpg"));
        setIcon(iconeBomba);
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_Marcado);
        setForeground(Color.BLACK);
        ImageIcon iconeMarcado = new ImageIcon(getClass().getResource("./bandeira.png"));
        setIcon(iconeMarcado);
    }

    private void aplicarEstiloAbrir() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if(campo.isMinado()){
            setBackground(BG_Explosao);
            return;
        }
        setBackground(BG_Padrao);
        switch (campo.minasNaVizinhanca()){
            case 1:
                setForeground(Text_Verde);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4:
            case 5:
            case 6:
                setForeground(Color.RED);
                break;
            default:
                setForeground(Color.PINK);

        }
        String valor = !campo.vizinhacaSegura() ? campo.minasNaVizinhanca() + "":"";
        setText(valor);
    }

    //Interface dos eventos do Mouse
    @Override
    public void mousePressed(MouseEvent e){
        if (e.getButton()==1){
            campo.abrir();
        }else {
            campo.alternarMarcacao();
        }
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
