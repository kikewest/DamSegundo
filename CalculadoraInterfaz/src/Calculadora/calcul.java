package Calculadora;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class calcul extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button10;
	private JButton button11;
	private JButton buttonIgual;
	private JButton buttonSuma;
	private JButton buttonResta;
	private JButton buttonMultipli;
	private JButton buttonDivision;
	private JButton buttonRaiz;
	private JTextField texField_insertadodeDatos;
	private JButton buttonlimpiar;
	private JLabel label_muestraValores_Arriba;
	String memoria1="";
	String memoria2="";
	String signo="";
	String muestra_resultado_en_Text;
	float resultadoSuma=0;
	float resultadoResta=0;
	float resultadoMultiplicacion=0;
	float resultadoDivision=0;
	float resultadoRaiz=0;
	float numero1=0;
	float numero2=0;
	private JButton buttonlimpiar_1;
	private JButton btnCe;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calcul frame = new calcul();
					frame.setVisible(true);
					frame.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public calcul() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Calculadora");
		setLocationRelativeTo(null);//ESTO ES PARA MOSTRAR LA CALCULADORA EN EL MEDIO DEL ORDENADOR
		texField_insertadodeDatos = new JTextField();
		texField_insertadodeDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		texField_insertadodeDatos.setForeground(Color.WHITE);
		texField_insertadodeDatos.setBackground(Color.DARK_GRAY);
		texField_insertadodeDatos.setHorizontalAlignment(SwingConstants.RIGHT);
		texField_insertadodeDatos.setColumns(10);
		texField_insertadodeDatos.setBounds(10, 58, 330, 50);
		contentPane.add(texField_insertadodeDatos);

		button1 = new JButton("1");
		button1.setForeground(Color.WHITE);
		button1.setBackground(Color.LIGHT_GRAY);
		button1.setFont(new Font("Tahoma", Font.BOLD, 13));
		button1.addActionListener(this);
		button1.setBounds(10, 302, 75, 50);
		contentPane.add(button1);


		button2 = new JButton("2");
		button2.setForeground(Color.WHITE);
		button2.setBackground(Color.LIGHT_GRAY);
		button2.setFont(new Font("Tahoma", Font.BOLD, 13));
		button2.addActionListener(this);
		button2.setBounds(95, 302, 75, 50);
		contentPane.add(button2);

		button3 = new JButton("3");
		button3.setForeground(Color.WHITE);
		button3.setBackground(Color.LIGHT_GRAY);
		button3.setFont(new Font("Tahoma", Font.BOLD, 13));
		button3.addActionListener(this);
		button3.setBounds(180, 302, 75, 50);
		contentPane.add(button3);

		button4 = new JButton("4");
		button4.setForeground(Color.WHITE);
		button4.setBackground(Color.LIGHT_GRAY);
		button4.setFont(new Font("Tahoma", Font.BOLD, 13));
		button4.addActionListener(this);
		button4.setBounds(10, 241, 75, 50);
		contentPane.add(button4);

		button5 = new JButton("5");
		button5.setForeground(Color.WHITE);
		button5.setBackground(Color.LIGHT_GRAY);
		button5.setFont(new Font("Tahoma", Font.BOLD, 13));
		button5.addActionListener(this);
		button5.setBounds(95, 241, 75, 50);
		contentPane.add(button5);

		button6 = new JButton("6");
		button6.setForeground(Color.WHITE);
		button6.setBackground(Color.LIGHT_GRAY);
		button6.setFont(new Font("Tahoma", Font.BOLD, 13));
		button6.addActionListener(this);
		button6.setBounds(180, 241, 75, 50);
		contentPane.add(button6);

		button7 = new JButton("7");
		button7.setForeground(Color.WHITE);
		button7.setBackground(Color.LIGHT_GRAY);
		button7.setFont(new Font("Tahoma", Font.BOLD, 13));
		button7.addActionListener(this);
		button7.setBounds(10, 180, 75, 50);
		contentPane.add(button7);

		button8 = new JButton("8");
		button8.setForeground(Color.WHITE);
		button8.setBackground(Color.LIGHT_GRAY);
		button8.setFont(new Font("Tahoma", Font.BOLD, 13));
		button8.addActionListener(this);
		button8.setBounds(95, 180, 75, 50);
		contentPane.add(button8);

		button9 = new JButton("9");
		button9.setForeground(Color.WHITE);
		button9.setBackground(Color.LIGHT_GRAY);
		button9.setFont(new Font("Tahoma", Font.BOLD, 13));
		button9.addActionListener(this);
		button9.setBounds(180, 180, 75, 50);
		contentPane.add(button9);

		button10 = new JButton("0");
		button10.setForeground(Color.WHITE);
		button10.setBackground(Color.LIGHT_GRAY);
		button10.setFont(new Font("Tahoma", Font.BOLD, 13));
		button10.addActionListener(this);
		button10.setBounds(95, 363, 75, 50);
		contentPane.add(button10);

		button11 = new JButton(".");
		button11.setBackground(Color.GRAY);
		button11.setForeground(Color.WHITE);
		button11.setFont(new Font("Tahoma", Font.BOLD, 14));
		button11.setBounds(180, 362, 75, 50);
		button11.addActionListener(this);
		contentPane.add(button11);

		buttonlimpiar = new JButton("C");
		buttonlimpiar.setForeground(Color.WHITE);
		buttonlimpiar.setBackground(Color.GRAY);
		buttonlimpiar.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonlimpiar.addActionListener(this);
		buttonlimpiar.setBounds(95, 119, 75, 50);
		contentPane.add(buttonlimpiar);

		buttonIgual = new JButton("=");
		buttonIgual.setBackground(new Color(173, 255, 47));
		buttonIgual.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonIgual.addActionListener(this);
		buttonIgual.setBounds(265, 361, 75, 50);
		contentPane.add(buttonIgual);

		buttonSuma = new JButton("+");
		buttonSuma.setBackground(Color.GRAY);
		buttonSuma.setForeground(Color.WHITE);
		buttonSuma.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonSuma.addActionListener(this);
		buttonSuma.setBounds(265, 302, 75, 50);
		contentPane.add(buttonSuma);

		buttonResta = new JButton("-");
		buttonResta.setBackground(Color.GRAY);
		buttonResta.setForeground(Color.WHITE);
		buttonResta.setFont(new Font("Tahoma", Font.BOLD, 18));
		buttonResta.addActionListener(this);
		buttonResta.setBounds(265, 239, 75, 50);
		contentPane.add(buttonResta);

		buttonMultipli = new JButton("x");
		buttonMultipli.setBackground(Color.GRAY);
		buttonMultipli.setForeground(Color.WHITE);
		buttonMultipli.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonMultipli.addActionListener(this);
		buttonMultipli.setBounds(265, 180, 75, 50);
		contentPane.add(buttonMultipli);

		buttonDivision = new JButton("\u00F7");
		buttonDivision.setBackground(Color.GRAY);
		buttonDivision.setForeground(Color.WHITE);
		buttonDivision.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonDivision.addActionListener(this);
		buttonDivision.setBounds(265, 119, 75, 50);
		contentPane.add(buttonDivision);

		buttonRaiz = new JButton("\u221A");
		buttonRaiz.setBackground(Color.GRAY);
		buttonRaiz.setForeground(Color.WHITE);
		buttonRaiz.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonRaiz.addActionListener(this);
		buttonRaiz.setBounds(10, 363, 75, 50);
		contentPane.add(buttonRaiz);

		label_muestraValores_Arriba = new JLabel("");
		label_muestraValores_Arriba.setForeground(Color.WHITE);
		label_muestraValores_Arriba.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_muestraValores_Arriba.setHorizontalAlignment(SwingConstants.RIGHT);
		label_muestraValores_Arriba.setBounds(10, 18, 330, 29);
		contentPane.add(label_muestraValores_Arriba);
		
		buttonlimpiar_1 = new JButton("⌫");
		buttonlimpiar_1.setBackground(Color.GRAY);
		buttonlimpiar_1.setForeground(Color.WHITE);
		buttonlimpiar_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonlimpiar_1.setBounds(180, 119, 75, 50);
		buttonlimpiar_1.addActionListener(this);
		contentPane.add(buttonlimpiar_1);
		
		btnCe = new JButton("CE");
		btnCe.setForeground(Color.WHITE);
		btnCe.setBackground(Color.GRAY);
		btnCe.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCe.setBounds(10, 119, 75, 50);
		btnCe.addActionListener(this);
		contentPane.add(btnCe);

	}
	@SuppressWarnings("removal")
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).equals(buttonlimpiar_1)){// Botón "⌫"	    
		    String textoActual = texField_insertadodeDatos.getText();
		    if (!textoActual.isEmpty()) {
		        textoActual = textoActual.substring(0, textoActual.length() - 1);
		        texField_insertadodeDatos.setText(textoActual);
		        label_muestraValores_Arriba.setText(textoActual);
		    }
		}
		if(((JButton)e.getSource()).equals(btnCe)){//boton ce
			texField_insertadodeDatos.setText("");
		}
		if(((JButton)e.getSource()).equals(button1)){//Si el evento del boton que se esta escuchando equivale al boton 1
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"1"); //Aqui le digo que ponga el texto"1" y si vuelve a pulsar el boton que concatene "+1"
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}

		if(((JButton)e.getSource()).equals(button2)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"2");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());

		}
		if(((JButton)e.getSource()).equals(button3)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"3");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}

		if(((JButton)e.getSource()).equals(button4)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"4");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		if(((JButton)e.getSource()).equals(button5)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"5");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		if(((JButton)e.getSource()).equals(button6)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"6");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		if(((JButton)e.getSource()).equals(button7)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"7");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		if(((JButton)e.getSource()).equals(button8)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"8");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		if(((JButton)e.getSource()).equals(button9)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"9");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		if(((JButton)e.getSource()).equals(button10)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+"0");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		if(((JButton)e.getSource()).equals(button11)){
			texField_insertadodeDatos.setText(""+texField_insertadodeDatos.getText()+".");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
		//AQUI OPERADORES
		if(((JButton)e.getSource()).equals(buttonSuma)){
			if(texField_insertadodeDatos.getText().equals("")){//Aqui hago un control si texField_insertadodeDatos esta vacio,no se ha pulsado ningun boton
				System.runFinalization();//la operacion no hace nada
				texField_insertadodeDatos.setText("");//Y establesco el campo vacio para poder meter cadenas(memoria1)
			}else{
				signo = "Suma";//Aqui me he declarado signo como un String y que si apreto el boton de suma operacion //contendra la cadena "Suma" y asi lo mismo  para los demás operadores.
				memoria1 = texField_insertadodeDatos.getText();//Aqui agarro el string que contienne la cadena //texField_insertadodeDatos y se lo meto a memoria1
				numero1 = Float.parseFloat(memoria1);//Aqui convierto de String de memoria1 a float y se lo meto numero1 //(Aqui  he declarado numero1 como float arriba para poder sumar mas adelante)
				label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText()+" +");//insertamos en muestra valores la operacion a realizar
				texField_insertadodeDatos.setText("");//establesco el campo vacio para poder meter cadenas en otra variable (memoria2)
			}
		}

		if(((JButton)e.getSource()).equals(buttonResta)){     
			if(texField_insertadodeDatos.getText().equals("")){
				System.runFinalization();
				texField_insertadodeDatos.setText("");
			}else{
				signo = "Resta";
				label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText()+" -");
				memoria1 = texField_insertadodeDatos.getText();
				numero1 = Float.parseFloat(memoria1);
				texField_insertadodeDatos.setText("");
			}
		}

		if(((JButton)e.getSource()).equals(buttonMultipli)){
			if(texField_insertadodeDatos.getText().equals("")){
				System.runFinalization();
				texField_insertadodeDatos.setText("");
			}else{
				signo = "Multiplicacion";
				label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText()+" x");
				memoria1 = texField_insertadodeDatos.getText();
				numero1 = Float.parseFloat(memoria1);
				texField_insertadodeDatos.setText("");
			}
		}
		if(((JButton)e.getSource()).equals(buttonDivision)){
			if(texField_insertadodeDatos.getText().equals("")){
				System.runFinalization();
				texField_insertadodeDatos.setText("");
			}else{
				signo = "Division";
				label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText()+" /");
				memoria1 = texField_insertadodeDatos.getText();
				numero1 = Float.parseFloat(memoria1);
				texField_insertadodeDatos.setText("");
			}
		}
		if(((JButton)e.getSource()).equals(buttonRaiz)){
			if(texField_insertadodeDatos.getText().equals("")){
				System.runFinalization();
				texField_insertadodeDatos.setText("");
			}else{
				signo = "Raiz";
				label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText()+" \u221A");
				memoria1 = texField_insertadodeDatos.getText();
				numero1 = Float.parseFloat(memoria1);
				texField_insertadodeDatos.setText("");
			}
		}
		//Boton igual
		if(((JButton)e.getSource()).equals(buttonIgual)){
			operacion();//llamo al metodo operacion
			texField_insertadodeDatos.setText(muestra_resultado_en_Text); //muestro el resultado por pantalla
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());//muestro el resultado por la pantalla
			signo="Suma";// Aqui pongo que signo es suma este es un truco por si quiero seguir usando el //resultado //obtenido de las operaciones anteriores (de division resta  multi y suma)

			numero1=0;//limpio mi numero1 y numero2  dejandolos a 0 para poder seguir haciendooperaciones
			numero2=0;
		}
		if(((JButton)e.getSource()).equals(buttonlimpiar)){//Aqui limpio todo para seguir metiendo cadenas
			texField_insertadodeDatos.setText("");
			label_muestraValores_Arriba.setText(""+texField_insertadodeDatos.getText());
		}
	}
	//METODO OPERACION
	public void operacion(){
		if (signo == "Suma"){
			memoria2 = texField_insertadodeDatos.getText();//el string que continene la cadena texField_insertadodeDatos y se lo meto a memoria2 (sera el segundo dato ya que el insertar datos esta vacio)
			numero2=Float.parseFloat(memoria2);
			resultadoSuma = numero1+numero2;
			muestra_resultado_en_Text = Float.toString(resultadoSuma);//convertimos el resultado en string para mostrarlo por pantalla
			texField_insertadodeDatos.setText(muestra_resultado_en_Text);
		}else if(signo == "Resta"){
			memoria2 = texField_insertadodeDatos.getText();
			numero2=Float.parseFloat(memoria2);
			resultadoResta = numero1-numero2;
			muestra_resultado_en_Text = Float.toString(resultadoResta);
			texField_insertadodeDatos.setText(muestra_resultado_en_Text);
		}else if(signo == "Multiplicacion"){
			memoria2 = texField_insertadodeDatos.getText();
			numero2=Float.parseFloat(memoria2);
			resultadoMultiplicacion = numero1*numero2;
			muestra_resultado_en_Text = Float.toString(resultadoMultiplicacion);
			texField_insertadodeDatos.setText(muestra_resultado_en_Text);
		}else if(signo == "Division"){
			memoria2 = texField_insertadodeDatos.getText();
			numero2=Float.parseFloat(memoria2);
			resultadoDivision = numero1/numero2;
			muestra_resultado_en_Text = Float.toString(resultadoDivision);
			texField_insertadodeDatos.setText(muestra_resultado_en_Text);
		}else if (signo == "Raiz"){
			//no necesitamos memoria ni numero 2
			resultadoRaiz=(float) (Math.sqrt(numero1)) ;
			muestra_resultado_en_Text = Float.toString(resultadoRaiz);
			texField_insertadodeDatos.setText(muestra_resultado_en_Text);
		}
	}
	//
}