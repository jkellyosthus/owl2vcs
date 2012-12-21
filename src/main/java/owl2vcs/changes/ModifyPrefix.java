package owl2vcs.changes;

import java.util.Collection;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.CustomOntologyChangeVisitor;

public class ModifyPrefix extends PrefixChange {

    private final String newPrefix;

    public ModifyPrefix(final OWLOntology ont, final String prefixName,
            final String originalPrefix, final String newPrefix) {
        super(ont, prefixName, originalPrefix);
        this.newPrefix = newPrefix;
    }

    public String getNewPrefix() {
        return newPrefix;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MODIFY PREFIX: ");
        sb.append(getPrefixName().toString() + "=" + getNewPrefix().toString());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((newPrefix == null) ? 0 : newPrefix.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof ModifyPrefix))
            return false;
        ModifyPrefix other = (ModifyPrefix) obj;
        if (newPrefix == null) {
            if (other.newPrefix != null)
                return false;
        } else if (!newPrefix.equals(other.newPrefix))
            return false;
        return true;
    }

    @Override
    public void accept(final CustomOntologyChangeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Collection<String> getPrefixSignature() {
        final Collection<String> sig = super.getPrefixSignature();
        sig.add(getNewPrefix());
        return sig;
    }

    @Override
    public ModifyPrefixData getChangeData() {
        return new ModifyPrefixData(getPrefixName(), getPrefix(), getNewPrefix());
    }
}
