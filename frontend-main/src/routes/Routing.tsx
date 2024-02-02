import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { NotFount } from '../screens/404/NotFount'
import { About } from '../screens/about/About'
import { Home } from '../screens/home/Home'

export const Routing = () => {
	return (
		<BrowserRouter>
			<Routes>
				<Route path={'/'} element={<Home />} />
				<Route path={'/about'} element={<About />} />
				<Route path={'*'} element={<NotFount />} />
			</Routes>
		</BrowserRouter>
	)
}
