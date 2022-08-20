package wood.mike

import grails.web.Action

class CacheAccessController {

    def cacheAccessService

    def index() {
        [cacheContents: cacheAccessService.getCacheContents()]
    }

    def addToCache() {
        render template: 'cacheContents', model: [cacheContents: cacheAccessService.addToCache()]
    }

    def getLastAddedFromCache() {
        render cacheAccessService.getLastAddedFromCache()
    }
}
