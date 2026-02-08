package terakoyalabo.lifecycle.domain

interface ServiceNode :
    ResourceVerifiable,
    StatusPublishable,
    RetirementNoticable,
    ResourceReleasable
