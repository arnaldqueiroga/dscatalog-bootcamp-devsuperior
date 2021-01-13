import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductsResponse } from 'core/types/Product';
import Pagination from 'core/components/Pagination';
import { makeRequest } from 'core/utils/request';
import ProductCard from './components/ProductCard';
import ProductCardLoader from './components/Loaders/ProductCardLoader';
import './styles.scss';

const Catalog = () => {
    //quando a lista de produtos estiver disponível, popular um estado no componente e listar os produtos dinâmicamente
    const [productsResponse, setProductsResponse] = useState<ProductsResponse>();
    const [isLoading, setIsLoading] = useState(false); //por padrão, o estado deve ser falso pois o loading não deve aparecer. O loading só aparece quando fizermos a requisição
    const [activePage, setActivePage] = useState(0);

    console.log(productsResponse);

    //quando o componente iniciar, buscar a lista de ProductRepository
    useEffect(() => {
        const params = {
            page: activePage,
            linesPerPage: 12
        }
        
        // iniciar o loader
        setIsLoading(true);
        makeRequest({ url: '/products', params })
            .then(response => setProductsResponse(response.data))
            .finally(() => {
                //finalizar o loader
                setIsLoading(false);
            });
    }, [activePage]); //toda as vezes em que for selecionada uma nova página, este código será executado novamente

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de produtos
            </h1>
            <div className="catalog-products">
                {isLoading ? <ProductCardLoader /> : (
                    productsResponse?.content.map(product => (
                        <Link to={`/products/${product.id}`} key={product.id}>
                            <ProductCard product={product}/>
                        </Link>
                    ))
                )}
            </div>
            {productsResponse && (
                <Pagination 
                    totalPages={productsResponse.totalPages}
                    activePage={activePage}
                    onChange={page => setActivePage(page)}
                />
            )}
        </div>
    );
};

export default Catalog;