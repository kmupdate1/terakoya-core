package terakoyalabo.lifecycle.domain

interface TaskNode :
    ResourceVerifiable,
    StatePersistant,
    ResourceReleasable
