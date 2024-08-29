* 1 - getOrderHistory —> @GetMapping("/orders/history/{customerId}")
* 2 - getDesignerAverage —> @GetMapping("/average-rating/{id}")
* 3 - getRatingsForDesigner —> @GetMapping("/designer-ratings/{designerId}")
* 4 - getDesignerOrderHistory —> @GetMapping("/order-history/{designerId}")
* 5 - getTopDesigner —> @GetMapping("/top-designer")
* 6 - getFabricOrderHistory —> @GetMapping("/order-history/{fabricId}")
* 7 - getMerchantAverage —> @GetMapping("/average-rating/{id}")
* 8 - getRatingsForMerchant —> @GetMapping("/merchant-ratings/{merchantId}")
* 9 - rateMerchants —> @PostMapping("/merchant/{merchantId}/rate/{customerId}")
* 10 -rateTailors —> @PostMapping("/tailor/{tailorId}/rate/{customerId}")
* 11 - rateDesigners —> @PostMapping("/designer/{designerId}/rate/{customerId}")
* 12 - getTailorAverage —> @GetMapping("/average-rating/{id}")
* 13 - getRatingsForTailor —> @GetMapping("/tailor-ratings/{tailorId}")
* 14 - getTailorOrderHistory —> @GetMapping("/order-history/{tailorId}")
* 15 - getTopTailor —> @GetMapping("/top-designer")
* CRUD —> Tailor Service & controller 
* CRUD —> Rating Service & controller 
* CRUD —> Designer service & controller 

- Entity
	- Tailor 
	- Rating
	- Designer
- DTO
	- OrderHistoryDTO
	- TailorInfoDTO
	- DesignerInfoDTO
