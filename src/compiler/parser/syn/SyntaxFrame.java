package compiler.parser.syn;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import compiler.parser.syn.classes.Goal;
import compiler.parser.syn.visitor.PrettyPrintVisitor;

@SuppressWarnings("serial")
public class SyntaxFrame extends JFrame {
	
	public SyntaxFrame(JTree tree) {
		super("Parse Tree");
		add(new JScrollPane(tree));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				PrettyPrintVisitor ppv = new PrettyPrintVisitor();				
				Goal entryPoint = new Goal(SyntaxEngine.readTokens("tokens.txt")); // printing should start with Goal object accepting the visitor
				entryPoint.accept(ppv); //Print (and build visual parse tree)
				new SyntaxFrame(ppv.createVisualParseTree()); //Show the visual tree
			}
		});
	}
}
