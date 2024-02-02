import axios from 'axios'
import { IGetData, IPostData } from '../interfaces/Interfaces'

export const Services = {
	async pushData(data: IPostData) {
		const request = await axios.post<IPostData>(
			'http://localhost:8080/push-to-back/',
			data
		)
		return request.status
	},
	async getData() {
		const response = await axios.get<IGetData>(
			'http://localhost:8080/get-from-back/'
		)
		return response.data
	},
}
