package io.jenkins.plugins.gitlabbranchsource;

import hudson.Extension;
import javax.annotation.Nonnull;
import jenkins.scm.api.trait.SCMNavigatorContext;
import jenkins.scm.api.trait.SCMNavigatorTrait;
import jenkins.scm.api.trait.SCMNavigatorTraitDescriptor;
import jenkins.scm.impl.trait.Selection;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * A {@link Selection} trait that will restrict the discovery of repositories that have been
 * archived.
 */
public class ExcludeArchivedRepositoriesTrait extends SCMNavigatorTrait {

    /** Constructor for stapler. */
    @DataBoundConstructor
    public ExcludeArchivedRepositoriesTrait() {}

    /** {@inheritDoc} */
    @Override
    protected void decorateContext(SCMNavigatorContext<?, ?> context) {
        super.decorateContext(context);
        GitLabSCMNavigatorContext ctx = (GitLabSCMNavigatorContext) context;
        ctx.setExcludeArchivedRepositories(true);
    }

    /** Exclude archived repositories filter */
    @Symbol("gitLabExcludeArchivedRepositories")
    @Extension
    @Selection
    public static class DescriptorImpl extends SCMNavigatorTraitDescriptor {

        @Override
        public Class<? extends SCMNavigatorContext> getContextClass() {
            return GitLabSCMNavigatorContext.class;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return Messages.ExcludeArchivedRepositoriesTrait_displayName();
        }
    }
}
