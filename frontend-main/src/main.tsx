import ReactDOM from 'react-dom/client';
import './index.css';
import { Routing } from './routes/Routing';
import { ReactNode } from 'react';

const Main = ({ children }: { children: ReactNode }) => {
    return (
        <>
            <header>

            </header>
            <main className={'w-full mt-5'}>
                {children}
            </main>
        </>
    );
};

ReactDOM.createRoot(document.getElementById('root')!).render(
    <Main>
        <Routing />
    </Main>
);
