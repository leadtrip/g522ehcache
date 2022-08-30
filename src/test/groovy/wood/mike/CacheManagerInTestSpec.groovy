package wood.mike

import grails.plugin.cache.ehcache.GrailsEhcacheCacheManager
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.springframework.cache.support.SimpleValueWrapper
import spock.lang.Specification

/**
 * Test showing how to get access to the grailsCacheManager in the test class
 * Just adding def grailsCacheManager or @AutoWired does not work, need to get bean from context
 */
class CacheManagerInTestSpec extends Specification implements ServiceUnitTest<CacheAccessService>, DataTest{

    def grailsCacheManager

    def setup() {
        defineBeans {
            grailsCacheManager(GrailsEhcacheCacheManager)
        }
        grailsCacheManager = grailsApplication.getMainContext().getBean('grailsCacheManager')
    }

    void "test cache"() {
        given:
            def tree = 'tree'
        when:
            def cache = grailsCacheManager.getCache('myTestCache')
            cache.put( 1, tree )
        then:
            def res = cache.get( 1 )
            res instanceof SimpleValueWrapper
            tree == res.get()
    }
}
