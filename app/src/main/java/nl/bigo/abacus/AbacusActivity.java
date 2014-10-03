package nl.bigo.abacus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import mu.EvalVisitor;
import mu.MuLexer;
import mu.MuParser;
import mu.Value;

public class AbacusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_abacus);

        final Button button = (Button)findViewById(R.id.parse_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText in = (EditText)findViewById(R.id.input_text);
                TextView out = (TextView)findViewById(R.id.output_text);

                String source = in.getText().toString();

                try {
                    MuLexer lexer = new MuLexer(new ANTLRInputStream(source));
                    MuParser parser = new MuParser(new CommonTokenStream(lexer));
                    ParseTree tree = parser.expr();
                    EvalVisitor visitor = new EvalVisitor();
                    Value value = visitor.visit(tree);
                    out.setText(source + " = " + value);
                }
                catch (RecognitionException e) {
                    out.setText("Oops: " + e.getMessage());
                }
            }
        });
    }
}
