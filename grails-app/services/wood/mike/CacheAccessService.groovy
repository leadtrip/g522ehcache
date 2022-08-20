package wood.mike


import org.grails.plugin.cache.GrailsCacheManager

import java.util.concurrent.atomic.AtomicInteger

class CacheAccessService {

    AtomicInteger ai = new AtomicInteger()

    def wordService
    GrailsCacheManager grailsCacheManager

    def addToCache() {
        getCache().put( ai.incrementAndGet(), randomWord() )
        getCacheContents()
    }

    def randomWord() {
        wordService.randomWord()
    }

    def getCacheContents() {
        getCache().getAllKeys().collectEntries{[it, cache.get(it).get()]}
    }

    def getLastAddedFromCache() {
        getCache().get( ai.get() )?.get()
    }

    def clearCache() {
        getCache().clear()
        ai.set(0)
    }

    def getCache() {
        grailsCacheManager.getCache('myCache')  // see /src/main/resources/ehcache.xml
    }
}
