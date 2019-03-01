package com.github.madbrain.dojo.cqlator.test;

import com.github.madbrain.dojo.cqlator.CQLLexer;
import com.github.madbrain.dojo.cqlator.Token;
import com.github.madbrain.dojo.cqlator.TokenType;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CQLLexerTest {

    @Test
    public void canLexEmptyInput() {
        CQLLexer lexer = new CQLLexer("");
        Token token = lexer.nextToken();
        Assertions.assertThat(token).isEqualToComparingFieldByField(new Token(TokenType.EOF));
    }

    @Test
    public void canLexOneKeyword() {
        CQLLexer lexer = new CQLLexer("PRIMARY");
        Token token = lexer.nextToken();
        Assertions.assertThat(token).isEqualToComparingFieldByField(new Token(TokenType.PRIMARY));
    }

    @Test
    public void canLexMultipleKeywordsSeparatedBySpaces() {
        CQLLexer lexer = new CQLLexer("CREATE\ntable KeYsPaCe");

        List<Token> tokens = IntStream.range(0, 4).mapToObj(i -> lexer.nextToken()).collect(Collectors.toList());
        Assertions.assertThat(tokens.get(0)).isEqualToComparingFieldByField(new Token(TokenType.CREATE));
        Assertions.assertThat(tokens.get(1)).isEqualToComparingFieldByField(new Token(TokenType.TABLE));
        Assertions.assertThat(tokens.get(2)).isEqualToComparingFieldByField(new Token(TokenType.KEYSPACE));
        Assertions.assertThat(tokens.get(3)).isEqualToComparingFieldByField(new Token(TokenType.EOF));
    }

    @Test
    public void canLexIdentifiers() {
        CQLLexer lexer = new CQLLexer("foo foo_bar foo5");

        List<Token> tokens = IntStream.range(0, 4).mapToObj(i -> lexer.nextToken()).collect(Collectors.toList());
        Assertions.assertThat(tokens.get(0)).isEqualToComparingFieldByField(new Token(TokenType.IDENT, "foo"));
        Assertions.assertThat(tokens.get(1)).isEqualToComparingFieldByField(new Token(TokenType.IDENT, "foo_bar"));
        Assertions.assertThat(tokens.get(2)).isEqualToComparingFieldByField(new Token(TokenType.IDENT, "foo5"));
        Assertions.assertThat(tokens.get(3)).isEqualToComparingFieldByField(new Token(TokenType.EOF));
    }

    @Test
    public void canLexSimpleStringLiteral() {
        CQLLexer lexer = new CQLLexer("'hello world'");

        List<Token> tokens = IntStream.range(0, 4).mapToObj(i -> lexer.nextToken()).collect(Collectors.toList());
        Assertions.assertThat(tokens.get(0)).isEqualToComparingFieldByField(new Token(TokenType.STRING_LITERAL, "hello world"));
        Assertions.assertThat(tokens.get(1)).isEqualToComparingFieldByField(new Token(TokenType.EOF));
    }

    @Test
    public void canLexEscapedStringLiteral() {
        CQLLexer lexer = new CQLLexer("'j''aime l''aerobic'");

        List<Token> tokens = IntStream.range(0, 4).mapToObj(i -> lexer.nextToken()).collect(Collectors.toList());
        Assertions.assertThat(tokens.get(0)).isEqualToComparingFieldByField(new Token(TokenType.STRING_LITERAL, "j'aime l'aerobic"));
        Assertions.assertThat(tokens.get(1)).isEqualToComparingFieldByField(new Token(TokenType.EOF));
    }

    @Test
    public void canLexSymbols() {
        CQLLexer lexer = new CQLLexer(".,;()");

        List<Token> tokens = IntStream.range(0, 4).mapToObj(i -> lexer.nextToken()).collect(Collectors.toList());
        Assertions.assertThat(tokens.get(0)).isEqualToComparingFieldByField(new Token(TokenType.DOT));
        Assertions.assertThat(tokens.get(1)).isEqualToComparingFieldByField(new Token(TokenType.COMA));
        Assertions.assertThat(tokens.get(2)).isEqualToComparingFieldByField(new Token(TokenType.SEMI));
        Assertions.assertThat(tokens.get(3)).isEqualToComparingFieldByField(new Token(TokenType.L_PAR));
        Assertions.assertThat(tokens.get(4)).isEqualToComparingFieldByField(new Token(TokenType.R_PAR));
        Assertions.assertThat(tokens.get(5)).isEqualToComparingFieldByField(new Token(TokenType.EOF));
    }
}
