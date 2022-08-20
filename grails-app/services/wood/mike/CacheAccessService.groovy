package wood.mike

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.plugin.cache.GrailsCacheManager

import java.util.concurrent.atomic.AtomicInteger

@Transactional
class CacheAccessService {

    AtomicInteger ai = new AtomicInteger()

    GrailsCacheManager grailsCacheManager

    def addToCache() {
        def cache = grailsCacheManager.getCache('myCache')
        cache.put( ai.incrementAndGet(), randomWord() )
        getCacheContents()
    }

    def randomWord() {
        new JsonSlurper().parse( "https://random-words-api.vercel.app/word".toURL() ).word[0]
    }

    def getCacheContents() {
        def cache = grailsCacheManager.getCache('myCache')
        cache.getAllKeys().collectEntries{[it, cache.get(it).get()]}
    }

    def getLastAddedFromCache() {
        def cache = grailsCacheManager.getCache('myCache')
        cache.get( ai.get() )?.get()
    }
}
