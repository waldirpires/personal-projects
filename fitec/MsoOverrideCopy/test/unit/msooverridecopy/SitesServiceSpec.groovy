package msooverridecopy

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(SitesService)
class SitesServiceSpec extends Specification {

	def sitesService
	
    def setup() {
    }

    def cleanup() {
    }

    void "test getSites"() {
		sitesService.getSites()
		log.info "testing get sites"
    }
}
