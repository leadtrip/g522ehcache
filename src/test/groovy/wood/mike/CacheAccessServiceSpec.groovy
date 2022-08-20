package wood.mike

import grails.plugin.cache.ehcache.GrailsEhcacheCacheManager
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class CacheAccessServiceSpec extends Specification implements ServiceUnitTest<CacheAccessService>, DataTest{

    def wordService = Mock(WordService)

    def setup() {
        defineBeans {
            grailsCacheManager(GrailsEhcacheCacheManager)
        }

        service.wordService = wordService
    }

    void "test cache"() {
        when:
            def res = service.addToCache()
        then:
            1 * wordService.randomWord() >> 'Piggy'
            res == [1: 'Piggy']
        when:
            res = service.addToCache()
        then:
            1 * wordService.randomWord() >> 'Marmalade'
            res == [1: 'Piggy', 2: 'Marmalade']
        when:
            res = service.lastAddedFromCache
        then:
            res == 'Marmalade'
        when:
            res = service.clearCache()
        then:
            !res
    }
}
