package terakoyalabo.lifecycle.domain

interface DaemonNode :
    ResourceVerifiable,
    Retryable,
    StatusPublishable,
    ResourceReleasable
