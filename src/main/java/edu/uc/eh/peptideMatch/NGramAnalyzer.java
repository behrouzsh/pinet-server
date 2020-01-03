package edu.uc.eh.peptideMatch;

/**
 * Created by shamsabz on 9/20/17.
 */

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.util.Version;

public class NGramAnalyzer extends Analyzer {

    private StandardAnalyzer analyzer;
    private int nGram;

    public NGramAnalyzer(int nGram) {
        this.nGram = nGram;
        this.analyzer = new StandardAnalyzer(Version.LUCENE_46);
        this.analyzer.setMaxTokenLength(Integer.MAX_VALUE);
    }

    public NGramAnalyzer() {
        analyzer = new StandardAnalyzer(Version.LUCENE_46);
    }



    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {

        Tokenizer src = new NGramTokenizer(Version.LUCENE_46, reader, nGram, nGram);

        TokenStream tok = new StandardFilter(Version.LUCENE_46, src);
        tok = new LowerCaseFilter(Version.LUCENE_46, tok);

        return new TokenStreamComponents(src, tok) {
            @Override
            protected void setReader(final Reader reader) throws IOException {
                super.setReader(reader);
            }
        };
    }

}
