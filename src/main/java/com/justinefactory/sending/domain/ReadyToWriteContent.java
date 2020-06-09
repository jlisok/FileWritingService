package com.justinefactory.sending.domain;

import java.util.Objects;

public class ReadyToWriteContent<FormattedContent> {

    private final FormattedContent content;

    public ReadyToWriteContent(FormattedContent fc){
        content = fc;
    }

    public FormattedContent getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadyToWriteContent<FormattedContent> that = (ReadyToWriteContent<FormattedContent>) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

}
