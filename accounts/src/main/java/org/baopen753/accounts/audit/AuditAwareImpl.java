package org.baopen753.accounts.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

// the implementation contains a method that provides the auditor
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNT_MS");
    }
}
