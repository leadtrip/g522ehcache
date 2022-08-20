package wood.mike

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

    def clearCache() {
        cacheAccessService.clearCache()
        render template: 'cacheContents', model: [cacheContents: []]
    }
}
