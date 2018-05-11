package com.ericsson.cms.espn.domain



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SiteGroupCopyController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def sitesService

	def index(Integer max) {
		//        params.max = Math.min(max ?: 10, 100)
		//        respond SiteGroupCopy.list(params), model:[siteGroupCopyInstanceCount: SiteGroupCopy.count()]
		def sitesList = sitesService.getNumberOfMsosForSites()

		respond sitesList, model:[sitesList: sitesList, siteInstanceCount: sitesList.size()]

	}

	def show(SiteGroupCopy siteGroupCopyInstance) {
		respond siteGroupCopyInstance
	}

	def create() {
		def sitesList = sitesService.getSites()

		respond sitesList, model:[sitesList: sitesList, siteInstanceCount: sitesList.size()]
	}

	def done() {
		redirect index
	}

	@Transactional
	def executeCopy(SiteGroupCopy siteGroupCopyInstance) {
		if (siteGroupCopyInstance == null) {
			notFound()
			return
		}

		if (siteGroupCopyInstance.hasErrors()) {
			respond siteGroupCopyInstance.errors, view:'index'
			return
		}

		if (siteGroupCopyInstance && siteGroupCopyInstance.srcSiteId.equals(siteGroupCopyInstance.destSiteId)) {
			def site = sitesService.getSite(siteGroupCopyInstance.srcSiteId)
			request.withFormat {
				form {
					flash.message = message(code: 'default.samesite.message', args: [site.name])
					redirect action: "create", method: "GET"
				}
				'*'{ render status: NOT_FOUND }
			}
		} else {
			sitesService.executeSiteGroupCopy(siteGroupCopyInstance)
			//siteGroupCopyInstance.save flush:true

			request.withFormat {
				form {
					flash.message = message(code: 'default.copy.message', args: [
						message(code: 'siteGroupCopyInstance.label', default: 'SiteGroupCopy')
					])
					redirect action: "index", method: "GET"
				}
				'*' { respond siteGroupCopyInstance, [status: CREATED] }
			}
		}

	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'siteGroupCopyInstance.label', default: 'SiteGroupCopy'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
