
/**
 * Write a description of class Calculadora here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculadora extends Frame
{
    JTextField campo;
    Button [] botoes;
    String [] labels = {"1","2","3","+","4","5","6","-","7","8","9","*","C","0","=","/"};
    Tratador tratador;
    
    public Calculadora(){
        setTitle("Calculadora");
        setLocation(100, 100);
        setSize(300, 350);
        criarComponentes();
        setVisible(true);
    }
    
    private void criarComponentes(){
        
        Panel painel = new Panel();
        painel.setLayout(new GridLayout(4,4));
        
        botoes = new Button[labels.length];
        
        tratador = new Tratador();
        
        Font fonte = new Font("Arial", Font.BOLD,20);
        
        for (int i = 0; i < labels.length; i++){
            botoes[i] = new Button(labels[i]);
            botoes[i].setFont(fonte);
            botoes[i].addActionListener(tratador);
            painel.add(botoes[i]);
        }
        
        add(painel, BorderLayout.CENTER);
        
        campo = new JTextField(30);
        campo.setFont(fonte);
        //campo.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        campo.setHorizontalAlignment(JTextField.RIGHT);
        add(campo, BorderLayout.NORTH);
        
        addWindowListener(tratador);
    }
    
    public static void main(String [] args){
        Calculadora c = new Calculadora();
    }
    
    class Tratador extends WindowAdapter implements ActionListener{
        
        int [] valor = new int[2];
        int contador;
        String operacao = "";
        
        /**
         * metodo que trata os eventos dos botoes
         */
        public void actionPerformed(ActionEvent evt){
            
            //System.out.println(evt.getActionCommand());
            String label = evt.getActionCommand();
            boolean isNumber = false;
            int number = 0;
            
            try{
                number = Integer.parseInt(label);
                isNumber = true;
            }catch(NumberFormatException e){
            }
            
            if (isNumber){
                   campo.setText(campo.getText() + number);                   
                   valor[contador] = Integer.parseInt(campo.getText());
                } else if (label.equals("C")){
                    valor[0] = 0;
                    valor[1] = 0;
                    campo.setText("");
                    operacao = "";
                    contador = 0;
                } else if (label.equals("+") || label.equals("-") ||
                           label.equals("*") || label.equals("/")){
                    contador++;
                    operacao = label;
                    campo.setText("");
                } else { // =
                    
                    if (operacao.equals("+")){
                        valor[0] = valor[0] + valor[1];
                    } else if (operacao.equals("-")){
                        valor[0] = valor[0] - valor[1];
                    } else if (operacao.equals("*")){
                        valor[0] = valor[0] * valor[1];
                    } else if (operacao.equals("/")){
                        if (valor[1] == 0){
                            campo.setText("Divisao por zero");
                        } else {
                            valor[0] = valor[0] / valor[1];                            
                        }
                    }
                    campo.setText(""+valor[0]);
                    // modificacao 1;
                    contador = 0;
                }
        }
        
        /**
         * metodo que trata os eventos da tela
         */
        public void windowClosing(WindowEvent evt){
            System.exit(0);   
        }
        
    }
}
