{-# LANGUAGE ConstraintKinds, DataKinds, FlexibleContexts, GADTs,
 OverloadedStrings, PatternSynonyms, QuasiQuotes,
 ScopedTypeVariables, TemplateHaskell, TypeOperators, TypeApplications,
 ViewPatterns #-}

module Main (main) where

import Frames
import Frames.TH (rowGen, RowGen(..))
import Pipes hiding (Proxy)
import Numeric.LinearAlgebra
import qualified Data.Foldable as F
import Lens.Micro.Extras

-- template Haskell to create the Person type, and personParser
tableTypes' (rowGen "../pima.data")
            { rowTypeName = "Person"
            , columnNames = [ "npreg", "glu", "bp"
                            , "skin", "bmi", "ped", "age", "yy" ]
            , separator = " " }

-- create a data stream
dataStream :: MonadSafe m => Producer Person m ()
dataStream = readTableOpt personParser "../pima.data"

-- load full dataset
loadData :: IO (Frame Person)
loadData = inCoreAoS dataStream

-- create rows of covariate matrix
rec2l :: Person -> [Double]
rec2l r = [1.0, fromIntegral $ rgetField @Npreg r, fromIntegral $ rgetField @Glu r,
           fromIntegral $ rgetField @Bp r, fromIntegral $ rgetField @Skin r,
            rgetField @Bmi r, rgetField @Ped r, fromIntegral $ rgetField @Age r]

-- sum an hmatrix Vector
vsum :: Vector Double -> Double
vsum v = (konst 1 (size v) :: Vector Double) <.> v

-- log-likelihood
ll :: Matrix Double -> Vector Double -> Vector Double -> Double
ll x y b = (negate) (vsum (cmap log (
                              (scalar 1) + (cmap exp (cmap (negate) (
                                                         (((scalar 2) * y) - (scalar 1)) * (x #> b)
                                                         )
                                                     )))))

-- gradient
gll :: Matrix Double -> Vector Double -> Vector Double -> Vector Double
gll x y b = (tr x) #> (y - (scalar 1)/((scalar 1) + (cmap exp (-x #> b))))

-- one step of gradient ascent
oneStep :: Matrix Double -> Vector Double -> Double -> Vector Double -> Vector Double
oneStep x y lrate b0 = b0 + (scalar lrate) * (gll x y b0)

-- function for ascent
ascend :: Matrix Double -> Vector Double -> (Vector Double -> Vector Double) ->
  Vector Double -> Int -> Double -> Vector Double
ascend x y step init maxIts tol =
    go init maxIts
  where 
    go b0 itsLeft =
      let b1 = step b0
          d = norm_2 $ b1 - b0
          in if ((d < tol)||(itsLeft < 1)) then b1
                else go b1 (itsLeft - 1)
    
main :: IO ()
main = do
  putStrLn "Gradient ascent for a log likelihood"
  putStrLn "First load and process the data..."
  dat <- loadData
  let yl = (\x -> if x then 1.0 else 0.0) <$> F.toList (view yy <$> dat)
  let xl = rec2l <$> F.toList dat
  print $ head xl
  let y = vector yl
  print y
  let x = fromLists xl 
  disp 2 x
  putStrLn "Now run the gradient ascent"
  -- choose reasonable init, since gradient ascent is terrible...
  let init = fromList [-9.8, 0.1, 0, 0, 0, 0, 1.8, 0] :: Vector Double
  print init
  print $ ll x y init
  let opt = ascend x y (oneStep x y 1.0e-6) init 10000 0.0001
  print opt
  print $ ll x y opt
  putStrLn "Goodbye."


