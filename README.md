Grails app that uses the ehcache plugin although not using it with the expected annotations.

Instead, a cache is defined in /src/main/resources/ehcache.xml from which you can perform CRUD operations from the UI.

The cache is accessed using the GrailsCacheManager which, as we're using ehcache is a GrailsEhcacheCacheManager

A supporting word service runs on a fixed schedule fetching random words from a web service, this in turn caches
responses (not ehcache) to speed up the UI rendering.