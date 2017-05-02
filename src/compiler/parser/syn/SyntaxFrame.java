package compiler.parser.syn;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

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
				
				PrettyPrintVisitor ppv = new PrettyPrintVisitor();
				
				// User you syntax engine to do some shit here and there to get things parsed
				
				//This goal object should have been parsed by the engine first
				Goal entryPoint = new Goal(SyntaxEngine.readTokens("tokens.txt")); // printing should start with Goal object accepting the visitor
				entryPoint.accept(ppv); //Print (and build visual parse tree)
				new SyntaxFrame(ppv.createVisualParseTree()); //Show the visual tree
			}
		});
	}
}
