package wood.mike


import groovy.json.JsonSlurper
import org.springframework.scheduling.annotation.Scheduled

import java.util.concurrent.TimeUnit

class WordService {

    static lazyInit = false

    static List<String> WORDS = Collections.synchronizedList(new ArrayList<>());

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.SECONDS)
    void cacheWords() {
        if( WORDS.size() < 30 ) {
            log.info("Caching words, current cache ${WORDS.size()}")
            10.times {
                WORDS.add( getWordFromService() )
            }
        }
    }

    def randomWord() {
        if( WORDS.isEmpty() ) {
            return getWordFromService()
        }
        WORDS.remove(0)
    }

    def getWordFromService() {
        new JsonSlurper().parse("https://random-words-api.vercel.app/word".toURL()).word[0]
    }
}
