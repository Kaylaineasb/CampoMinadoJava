package campoMinadov2.visao;

import campoMinadov2.modelo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro  extends JPanel {
    public PainelTabuleiro(Tabuleiro tabuleiro){
        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCada(c->add(new BotaoCampo(c)));
        tabuleiro.registrarObservador(e->{
            SwingUtilities.invokeLater(()->{
                if (tabuleiro.objetivoAlcancado()){
                    JOptionPane.showMessageDialog(this,"Ganhou! :)");
                }else {
                    JOptionPane.showMessageDialog(this,"Perdeu! :(");
                }
                tabuleiro.reiniciar();
            });

        });
    }
}
